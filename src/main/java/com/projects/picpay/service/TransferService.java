package com.projects.picpay.service;

import com.projects.picpay.controller.dto.TransferDto;
import com.projects.picpay.entity.Transfer;
import com.projects.picpay.entity.Wallet;
import com.projects.picpay.exception.*;
import com.projects.picpay.repository.TransferRepository;
import com.projects.picpay.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {

    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final TransferRepository transferRepository;
    private final WalletRepository walletRepository;

    public TransferService(AuthorizationService authorizationService,
                           NotificationService notificationService,
                           TransferRepository transferRepository,
                           WalletRepository walletRepository) {
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.transferRepository = transferRepository;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Object transfer(TransferDto transferDto) {

        var sender = walletRepository.findById(transferDto.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));

        var reciver = walletRepository.findById(transferDto.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));

        validateTransfer(transferDto, sender);

        sender.debit(transferDto.value());
        reciver.credit(transferDto.value());

        var transfer = new Transfer(sender, reciver, transferDto.value());

        walletRepository.save(sender);
        walletRepository.save(reciver);
        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return transferResult;
    }

    private void validateTransfer(TransferDto transferDto, Wallet sender) {

        if(!sender.isTransferAllowedForWalletType()) {
            throw new TransferNotAllowedForWalletTypeException();
        }

        if(!sender.isBalancerEqualOrGreatherThan(transferDto.value())) {
            throw new InsufficientBalanceException();
        }

        if(!authorizationService.isAuthorized(transferDto)) {
            throw new TransferNotAuthorizedException();
        }
    }
}

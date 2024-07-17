package com.projects.picpay.service;

import com.projects.picpay.controller.dto.CreateWalletDto;
import com.projects.picpay.entity.Wallet;
import com.projects.picpay.exception.WalletDataAlreadyExistsException;
import com.projects.picpay.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }


    public Wallet createWallet(CreateWalletDto dto) {

        var walletDb = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());
        if(walletDb.isPresent()) {
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");
        }

        return walletRepository.save(dto.toWallet());
    }
}

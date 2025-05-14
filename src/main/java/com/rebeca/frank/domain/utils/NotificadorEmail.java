package com.rebeca.frank.domain.utils;

public class NotificadorEmail implements Notificador {
    @Override
    public void notificar(String message) {
        System.out.println("Notificação sendo enviada por EMAIL: " + message);
    }
}

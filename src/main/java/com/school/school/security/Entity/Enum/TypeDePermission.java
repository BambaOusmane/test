package com.school.school.security.Entity.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TypeDePermission {
    ADMINISTRATEUR_CREATE,
    ADMINISTRATEUR_READ,
    ADMINISTRATEUR_UPDATE,
    ADMINISTRATEUR_DELETE,

    MANAGER_CREATE,
    MANAGER_READ,
    MANAGER_UPDATE,
    MANAGER_DELETE_AVIS,


    UTILISATEUR_CREATE_AVIS;


    @Getter
    private String libelle;





}

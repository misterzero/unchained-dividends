import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { ExtendedUserComponent } from './extended-user.component';
import { ExtendedUserDetailComponent } from './extended-user-detail.component';
import { ExtendedUserPopupComponent } from './extended-user-dialog.component';
import { ExtendedUserDeletePopupComponent } from './extended-user-delete-dialog.component';

import { Principal } from '../../shared';

export const extendedUserRoute: Routes = [
    {
        path: 'extended-user',
        component: ExtendedUserComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'unchainedApp.extendedUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'extended-user/:id',
        component: ExtendedUserDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'unchainedApp.extendedUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const extendedUserPopupRoute: Routes = [
    {
        path: 'extended-user-new',
        component: ExtendedUserPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'unchainedApp.extendedUser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'extended-user/:id/edit',
        component: ExtendedUserPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'unchainedApp.extendedUser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'extended-user/:id/delete',
        component: ExtendedUserDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'unchainedApp.extendedUser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

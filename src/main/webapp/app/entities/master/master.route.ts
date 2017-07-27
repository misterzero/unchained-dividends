import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { MasterComponent } from './master.component';
import { MasterDetailComponent } from './master-detail.component';
import { MasterPopupComponent } from './master-dialog.component';
import { MasterDeletePopupComponent } from './master-delete-dialog.component';

import { Principal } from '../../shared';

export const masterRoute: Routes = [
    {
        path: 'master',
        component: MasterComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'unchainedApp.master.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'master/:id',
        component: MasterDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'unchainedApp.master.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const masterPopupRoute: Routes = [
    {
        path: 'master-new',
        component: MasterPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'unchainedApp.master.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'master/:id/edit',
        component: MasterPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'unchainedApp.master.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'master/:id/delete',
        component: MasterDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'unchainedApp.master.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { InvestorComponent } from './investor.component';
import { InvestorDetailComponent } from './investor-detail.component';
import { InvestorPopupComponent } from './investor-dialog.component';
import { InvestorDeletePopupComponent } from './investor-delete-dialog.component';

import { Principal } from '../../shared';

export const investorRoute: Routes = [
    {
        path: 'investor',
        component: InvestorComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'unchainedApp.investor.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'investor/:id',
        component: InvestorDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'unchainedApp.investor.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const investorPopupRoute: Routes = [
    {
        path: 'investor-new',
        component: InvestorPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'unchainedApp.investor.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'investor/:id/edit',
        component: InvestorPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'unchainedApp.investor.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'investor/:id/delete',
        component: InvestorDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'unchainedApp.investor.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

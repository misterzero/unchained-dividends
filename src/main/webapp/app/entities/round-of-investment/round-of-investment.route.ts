import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { RoundOfInvestmentComponent } from './round-of-investment.component';
import { RoundOfInvestmentDetailComponent } from './round-of-investment-detail.component';
import { RoundOfInvestmentPopupComponent } from './round-of-investment-dialog.component';
import { RoundOfInvestmentDeletePopupComponent } from './round-of-investment-delete-dialog.component';

import { Principal } from '../../shared';

export const roundOfInvestmentRoute: Routes = [
    {
        path: 'round-of-investment',
        component: RoundOfInvestmentComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'unchainedApp.roundOfInvestment.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'round-of-investment/:id',
        component: RoundOfInvestmentDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'unchainedApp.roundOfInvestment.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const roundOfInvestmentPopupRoute: Routes = [
    {
        path: 'round-of-investment-new',
        component: RoundOfInvestmentPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'unchainedApp.roundOfInvestment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'round-of-investment/:id/edit',
        component: RoundOfInvestmentPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'unchainedApp.roundOfInvestment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'round-of-investment/:id/delete',
        component: RoundOfInvestmentDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'unchainedApp.roundOfInvestment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

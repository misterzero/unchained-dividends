import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UnchainedSharedModule } from '../../shared';
import {
    InvestorService,
    InvestorPopupService,
    InvestorComponent,
    InvestorDetailComponent,
    InvestorDialogComponent,
    InvestorPopupComponent,
    InvestorDeletePopupComponent,
    InvestorDeleteDialogComponent,
    investorRoute,
    investorPopupRoute,
} from './';

const ENTITY_STATES = [
    ...investorRoute,
    ...investorPopupRoute,
];

@NgModule({
    imports: [
        UnchainedSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        InvestorComponent,
        InvestorDetailComponent,
        InvestorDialogComponent,
        InvestorDeleteDialogComponent,
        InvestorPopupComponent,
        InvestorDeletePopupComponent,
    ],
    entryComponents: [
        InvestorComponent,
        InvestorDialogComponent,
        InvestorPopupComponent,
        InvestorDeleteDialogComponent,
        InvestorDeletePopupComponent,
    ],
    providers: [
        InvestorService,
        InvestorPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UnchainedInvestorModule {}

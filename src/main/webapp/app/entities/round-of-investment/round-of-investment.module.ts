import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UnchainedSharedModule } from '../../shared';
import {
    RoundOfInvestmentService,
    RoundOfInvestmentPopupService,
    RoundOfInvestmentComponent,
    RoundOfInvestmentDetailComponent,
    RoundOfInvestmentDialogComponent,
    RoundOfInvestmentPopupComponent,
    RoundOfInvestmentDeletePopupComponent,
    RoundOfInvestmentDeleteDialogComponent,
    roundOfInvestmentRoute,
    roundOfInvestmentPopupRoute,
} from './';

const ENTITY_STATES = [
    ...roundOfInvestmentRoute,
    ...roundOfInvestmentPopupRoute,
];

@NgModule({
    imports: [
        UnchainedSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        RoundOfInvestmentComponent,
        RoundOfInvestmentDetailComponent,
        RoundOfInvestmentDialogComponent,
        RoundOfInvestmentDeleteDialogComponent,
        RoundOfInvestmentPopupComponent,
        RoundOfInvestmentDeletePopupComponent,
    ],
    entryComponents: [
        RoundOfInvestmentComponent,
        RoundOfInvestmentDialogComponent,
        RoundOfInvestmentPopupComponent,
        RoundOfInvestmentDeleteDialogComponent,
        RoundOfInvestmentDeletePopupComponent,
    ],
    providers: [
        RoundOfInvestmentService,
        RoundOfInvestmentPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UnchainedRoundOfInvestmentModule {}

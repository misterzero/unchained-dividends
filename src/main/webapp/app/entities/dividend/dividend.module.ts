import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UnchainedSharedModule } from '../../shared';
import {
    DividendService,
    DividendPopupService,
    DividendComponent,
    DividendDetailComponent,
    DividendDialogComponent,
    DividendPopupComponent,
    DividendDeletePopupComponent,
    DividendDeleteDialogComponent,
    dividendRoute,
    dividendPopupRoute,
} from './';

const ENTITY_STATES = [
    ...dividendRoute,
    ...dividendPopupRoute,
];

@NgModule({
    imports: [
        UnchainedSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        DividendComponent,
        DividendDetailComponent,
        DividendDialogComponent,
        DividendDeleteDialogComponent,
        DividendPopupComponent,
        DividendDeletePopupComponent,
    ],
    entryComponents: [
        DividendComponent,
        DividendDialogComponent,
        DividendPopupComponent,
        DividendDeleteDialogComponent,
        DividendDeletePopupComponent,
    ],
    providers: [
        DividendService,
        DividendPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UnchainedDividendModule {}

import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UnchainedSharedModule } from '../../shared';
import {
    MasterService,
    MasterPopupService,
    MasterComponent,
    MasterDetailComponent,
    MasterDialogComponent,
    MasterPopupComponent,
    MasterDeletePopupComponent,
    MasterDeleteDialogComponent,
    masterRoute,
    masterPopupRoute,
} from './';

const ENTITY_STATES = [
    ...masterRoute,
    ...masterPopupRoute,
];

@NgModule({
    imports: [
        UnchainedSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        MasterComponent,
        MasterDetailComponent,
        MasterDialogComponent,
        MasterDeleteDialogComponent,
        MasterPopupComponent,
        MasterDeletePopupComponent,
    ],
    entryComponents: [
        MasterComponent,
        MasterDialogComponent,
        MasterPopupComponent,
        MasterDeleteDialogComponent,
        MasterDeletePopupComponent,
    ],
    providers: [
        MasterService,
        MasterPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UnchainedMasterModule {}

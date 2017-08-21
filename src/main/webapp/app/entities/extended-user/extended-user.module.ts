import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UnchainedSharedModule } from '../../shared';
import {
    ExtendedUserService,
    ExtendedUserPopupService,
    ExtendedUserComponent,
    ExtendedUserDetailComponent,
    ExtendedUserDialogComponent,
    ExtendedUserPopupComponent,
    ExtendedUserDeletePopupComponent,
    ExtendedUserDeleteDialogComponent,
    extendedUserRoute,
    extendedUserPopupRoute,
} from './';

const ENTITY_STATES = [
    ...extendedUserRoute,
    ...extendedUserPopupRoute,
];

@NgModule({
    imports: [
        UnchainedSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ExtendedUserComponent,
        ExtendedUserDetailComponent,
        ExtendedUserDialogComponent,
        ExtendedUserDeleteDialogComponent,
        ExtendedUserPopupComponent,
        ExtendedUserDeletePopupComponent,
    ],
    entryComponents: [
        ExtendedUserComponent,
        ExtendedUserDialogComponent,
        ExtendedUserPopupComponent,
        ExtendedUserDeleteDialogComponent,
        ExtendedUserDeletePopupComponent,
    ],
    providers: [
        ExtendedUserService,
        ExtendedUserPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UnchainedExtendedUserModule {}

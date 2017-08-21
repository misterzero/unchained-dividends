import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ChartsModule } from 'ng2-charts';

import { UnchainedSharedModule } from '../shared';

import { HOME_ROUTE, HomeComponent } from './';
import { HomeService } from './';

@NgModule({
    imports: [
        UnchainedSharedModule,
        RouterModule.forRoot([ HOME_ROUTE ], { useHash: true }),
        ChartsModule
    ],
    declarations: [
        HomeComponent,
    ],
    entryComponents: [
    ],
    providers: [
      HomeService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UnchainedHomeModule {}

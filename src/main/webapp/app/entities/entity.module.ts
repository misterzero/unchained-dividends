import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { UnchainedInvestorModule } from './investor/investor.module';
import { UnchainedMasterModule } from './master/master.module';
import { UnchainedDividendModule } from './dividend/dividend.module';
import { UnchainedRoundOfInvestmentModule } from './round-of-investment/round-of-investment.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        UnchainedInvestorModule,
        UnchainedMasterModule,
        UnchainedDividendModule,
        UnchainedRoundOfInvestmentModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UnchainedEntityModule {}

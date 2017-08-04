import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager, ParseLinks, PaginationUtil, JhiLanguageService, AlertService } from 'ng-jhipster';

import { Investor } from './investor.model';
import { InvestorService } from './investor.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-investor',
    templateUrl: './investor.component.html'
})
export class InvestorComponent implements OnInit, OnDestroy {
    investors: Investor[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private investorService: InvestorService,
        private alertService: AlertService,
        private eventManager: EventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.investorService.query().subscribe(
            (res: ResponseWrapper) => {
                this.investors = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInInvestors();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Investor) {
        return item.id;
    }
    registerChangeInInvestors() {
        this.eventSubscriber = this.eventManager.subscribe('investorListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

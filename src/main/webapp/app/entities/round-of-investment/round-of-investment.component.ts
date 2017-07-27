import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager, ParseLinks, PaginationUtil, JhiLanguageService, AlertService } from 'ng-jhipster';

import { RoundOfInvestment } from './round-of-investment.model';
import { RoundOfInvestmentService } from './round-of-investment.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-round-of-investment',
    templateUrl: './round-of-investment.component.html'
})
export class RoundOfInvestmentComponent implements OnInit, OnDestroy {
roundOfInvestments: RoundOfInvestment[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private roundOfInvestmentService: RoundOfInvestmentService,
        private alertService: AlertService,
        private eventManager: EventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.roundOfInvestmentService.query().subscribe(
            (res: ResponseWrapper) => {
                this.roundOfInvestments = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInRoundOfInvestments();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: RoundOfInvestment) {
        return item.id;
    }
    registerChangeInRoundOfInvestments() {
        this.eventSubscriber = this.eventManager.subscribe('roundOfInvestmentListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

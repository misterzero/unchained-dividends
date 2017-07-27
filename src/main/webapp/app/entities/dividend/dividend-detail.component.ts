import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager  } from 'ng-jhipster';

import { Dividend } from './dividend.model';
import { DividendService } from './dividend.service';

@Component({
    selector: 'jhi-dividend-detail',
    templateUrl: './dividend-detail.component.html'
})
export class DividendDetailComponent implements OnInit, OnDestroy {

    dividend: Dividend;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private dividendService: DividendService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInDividends();
    }

    load(id) {
        this.dividendService.find(id).subscribe((dividend) => {
            this.dividend = dividend;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInDividends() {
        this.eventSubscriber = this.eventManager.subscribe(
            'dividendListModification',
            (response) => this.load(this.dividend.id)
        );
    }
}

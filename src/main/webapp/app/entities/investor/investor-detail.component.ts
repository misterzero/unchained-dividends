import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager  } from 'ng-jhipster';

import { Investor } from './investor.model';
import { InvestorService } from './investor.service';

@Component({
    selector: 'jhi-investor-detail',
    templateUrl: './investor-detail.component.html'
})
export class InvestorDetailComponent implements OnInit, OnDestroy {

    investor: Investor;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private investorService: InvestorService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInInvestors();
    }

    load(id) {
        this.investorService.find(id).subscribe((investor) => {
            this.investor = investor;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInInvestors() {
        this.eventSubscriber = this.eventManager.subscribe(
            'investorListModification',
            (response) => this.load(this.investor.id)
        );
    }
}

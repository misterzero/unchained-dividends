import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager  } from 'ng-jhipster';

import { RoundOfInvestment } from './round-of-investment.model';
import { RoundOfInvestmentService } from './round-of-investment.service';

@Component({
    selector: 'jhi-round-of-investment-detail',
    templateUrl: './round-of-investment-detail.component.html'
})
export class RoundOfInvestmentDetailComponent implements OnInit, OnDestroy {

    roundOfInvestment: RoundOfInvestment;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private roundOfInvestmentService: RoundOfInvestmentService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInRoundOfInvestments();
    }

    load(id) {
        this.roundOfInvestmentService.find(id).subscribe((roundOfInvestment) => {
            this.roundOfInvestment = roundOfInvestment;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInRoundOfInvestments() {
        this.eventSubscriber = this.eventManager.subscribe(
            'roundOfInvestmentListModification',
            (response) => this.load(this.roundOfInvestment.id)
        );
    }
}

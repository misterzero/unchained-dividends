import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager  } from 'ng-jhipster';

import { Master } from './master.model';
import { MasterService } from './master.service';

@Component({
    selector: 'jhi-master-detail',
    templateUrl: './master-detail.component.html'
})
export class MasterDetailComponent implements OnInit, OnDestroy {

    master: Master;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private masterService: MasterService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInMasters();
    }

    load(id) {
        this.masterService.find(id).subscribe((master) => {
            this.master = master;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInMasters() {
        this.eventSubscriber = this.eventManager.subscribe(
            'masterListModification',
            (response) => this.load(this.master.id)
        );
    }
}

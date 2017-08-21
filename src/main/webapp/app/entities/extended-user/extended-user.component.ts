import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager, ParseLinks, PaginationUtil, JhiLanguageService, AlertService } from 'ng-jhipster';

import { ExtendedUser } from './extended-user.model';
import { ExtendedUserService } from './extended-user.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-extended-user',
    templateUrl: './extended-user.component.html'
})
export class ExtendedUserComponent implements OnInit, OnDestroy {
extendedUsers: ExtendedUser[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private extendedUserService: ExtendedUserService,
        private alertService: AlertService,
        private eventManager: EventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.extendedUserService.query().subscribe(
            (res: ResponseWrapper) => {
                this.extendedUsers = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInExtendedUsers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ExtendedUser) {
        return item.id;
    }
    registerChangeInExtendedUsers() {
        this.eventSubscriber = this.eventManager.subscribe('extendedUserListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

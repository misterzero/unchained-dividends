import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Dividend } from './dividend.model';
import { DividendPopupService } from './dividend-popup.service';
import { DividendService } from './dividend.service';

@Component({
    selector: 'jhi-dividend-dialog',
    templateUrl: './dividend-dialog.component.html'
})
export class DividendDialogComponent implements OnInit {

    dividend: Dividend;
    authorities: any[];
    isSaving: boolean;
    dateDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private dividendService: DividendService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = false;
        if (this.dividend.id !== undefined) {
            this.subscribeToSaveResponse(
                this.dividendService.update(this.dividend), false);
        } else {
            this.subscribeToSaveResponse(
                this.dividendService.create(this.dividend), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<Dividend>, isCreated: boolean) {
        result.subscribe((res: Dividend) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Dividend, isCreated: boolean) {
        this.alertService.success(
            isCreated ? 'unchainedApp.dividend.created'
            : 'unchainedApp.dividend.updated',
            { param : result.id }, null);

        this.eventManager.broadcast({ name: 'dividendListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-dividend-popup',
    template: ''
})
export class DividendPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private dividendPopupService: DividendPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.dividendPopupService
                    .open(DividendDialogComponent, params['id']);
            } else {
                this.modalRef = this.dividendPopupService
                    .open(DividendDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

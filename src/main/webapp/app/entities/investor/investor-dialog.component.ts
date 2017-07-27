import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Investor } from './investor.model';
import { InvestorPopupService } from './investor-popup.service';
import { InvestorService } from './investor.service';

@Component({
    selector: 'jhi-investor-dialog',
    templateUrl: './investor-dialog.component.html'
})
export class InvestorDialogComponent implements OnInit {

    investor: Investor;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private investorService: InvestorService,
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
        this.isSaving = true;
        if (this.investor.id !== undefined) {
            this.subscribeToSaveResponse(
                this.investorService.update(this.investor), false);
        } else {
            this.subscribeToSaveResponse(
                this.investorService.create(this.investor), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<Investor>, isCreated: boolean) {
        result.subscribe((res: Investor) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Investor, isCreated: boolean) {
        this.alertService.success(
            isCreated ? 'unchainedApp.investor.created'
            : 'unchainedApp.investor.updated',
            { param : result.id }, null);

        this.eventManager.broadcast({ name: 'investorListModification', content: 'OK'});
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
    selector: 'jhi-investor-popup',
    template: ''
})
export class InvestorPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private investorPopupService: InvestorPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.investorPopupService
                    .open(InvestorDialogComponent, params['id']);
            } else {
                this.modalRef = this.investorPopupService
                    .open(InvestorDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

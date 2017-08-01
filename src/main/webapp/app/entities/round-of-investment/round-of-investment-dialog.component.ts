import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { RoundOfInvestment } from './round-of-investment.model';
import { RoundOfInvestmentPopupService } from './round-of-investment-popup.service';
import { RoundOfInvestmentService } from './round-of-investment.service';

@Component({
    selector: 'jhi-round-of-investment-dialog',
    templateUrl: './round-of-investment-dialog.component.html'
})
export class RoundOfInvestmentDialogComponent implements OnInit {

    roundOfInvestment: RoundOfInvestment;
    authorities: any[];
    isSaving: boolean;
    endDateDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private roundOfInvestmentService: RoundOfInvestmentService,
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
        if (this.roundOfInvestment.id !== undefined) {
            this.subscribeToSaveResponse(
                this.roundOfInvestmentService.update(this.roundOfInvestment), false);
        } else {
            this.subscribeToSaveResponse(
                this.roundOfInvestmentService.create(this.roundOfInvestment), true);
                this.eventManager.broadcast({ name: 'roundOfInvestmentListModification', content: 'OK'});
                this.activeModal.dismiss();
        }
    }

    private subscribeToSaveResponse(result: Observable<RoundOfInvestment>, isCreated: boolean) {
        result.subscribe((res: RoundOfInvestment) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: RoundOfInvestment, isCreated: boolean) {
        this.alertService.success(
            isCreated ? 'unchainedApp.roundOfInvestment.created'
            : 'unchainedApp.roundOfInvestment.updated',
            { param : result.id }, null);

        this.eventManager.broadcast({ name: 'roundOfInvestmentListModification', content: 'OK'});
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
    selector: 'jhi-round-of-investment-popup',
    template: ''
})
export class RoundOfInvestmentPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private roundOfInvestmentPopupService: RoundOfInvestmentPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.roundOfInvestmentPopupService
                    .open(RoundOfInvestmentDialogComponent, params['id']);
            } else {
                this.modalRef = this.roundOfInvestmentPopupService
                    .open(RoundOfInvestmentDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

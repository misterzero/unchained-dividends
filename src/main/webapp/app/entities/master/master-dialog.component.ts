import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Master } from './master.model';
import { MasterPopupService } from './master-popup.service';
import { MasterService } from './master.service';

@Component({
    selector: 'jhi-master-dialog',
    templateUrl: './master-dialog.component.html'
})
export class MasterDialogComponent implements OnInit {

    master: Master;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private masterService: MasterService,
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
        if (this.master.id !== undefined) {
            this.subscribeToSaveResponse(
                this.masterService.update(this.master), false);
        } else {
            this.subscribeToSaveResponse(
                this.masterService.create(this.master), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<Master>, isCreated: boolean) {
        result.subscribe((res: Master) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Master, isCreated: boolean) {
        this.alertService.success(
            isCreated ? 'unchainedApp.master.created'
            : 'unchainedApp.master.updated',
            { param : result.id }, null);

        this.eventManager.broadcast({ name: 'masterListModification', content: 'OK'});
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
    selector: 'jhi-master-popup',
    template: ''
})
export class MasterPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private masterPopupService: MasterPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.masterPopupService
                    .open(MasterDialogComponent, params['id']);
            } else {
                this.modalRef = this.masterPopupService
                    .open(MasterDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

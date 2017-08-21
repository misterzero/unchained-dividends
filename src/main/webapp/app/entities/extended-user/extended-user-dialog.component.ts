import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { ExtendedUser } from './extended-user.model';
import { ExtendedUserPopupService } from './extended-user-popup.service';
import { ExtendedUserService } from './extended-user.service';

@Component({
    selector: 'jhi-extended-user-dialog',
    templateUrl: './extended-user-dialog.component.html'
})
export class ExtendedUserDialogComponent implements OnInit {

    extendedUser: ExtendedUser;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private extendedUserService: ExtendedUserService,
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
        if (this.extendedUser.id !== undefined) {
            this.subscribeToSaveResponse(
                this.extendedUserService.update(this.extendedUser), false);
        } else {
            this.subscribeToSaveResponse(
                this.extendedUserService.create(this.extendedUser), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<ExtendedUser>, isCreated: boolean) {
        result.subscribe((res: ExtendedUser) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: ExtendedUser, isCreated: boolean) {
        this.alertService.success(
            isCreated ? 'unchainedApp.extendedUser.created'
            : 'unchainedApp.extendedUser.updated',
            { param : result.id }, null);

        this.eventManager.broadcast({ name: 'extendedUserListModification', content: 'OK'});
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
    selector: 'jhi-extended-user-popup',
    template: ''
})
export class ExtendedUserPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private extendedUserPopupService: ExtendedUserPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.extendedUserPopupService
                    .open(ExtendedUserDialogComponent, params['id']);
            } else {
                this.modalRef = this.extendedUserPopupService
                    .open(ExtendedUserDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

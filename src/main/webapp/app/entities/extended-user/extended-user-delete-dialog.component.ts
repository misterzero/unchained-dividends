import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AlertService, EventManager } from 'ng-jhipster';

import { ExtendedUser } from './extended-user.model';
import { ExtendedUserPopupService } from './extended-user-popup.service';
import { ExtendedUserService } from './extended-user.service';

@Component({
    selector: 'jhi-extended-user-delete-dialog',
    templateUrl: './extended-user-delete-dialog.component.html'
})
export class ExtendedUserDeleteDialogComponent {

    extendedUser: ExtendedUser;

    constructor(
        private extendedUserService: ExtendedUserService,
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private eventManager: EventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.extendedUserService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'extendedUserListModification',
                content: 'Deleted an extendedUser'
            });
            this.activeModal.dismiss(true);
        });
        this.alertService.success('unchainedApp.extendedUser.deleted', { param : id }, null);
    }
}

@Component({
    selector: 'jhi-extended-user-delete-popup',
    template: ''
})
export class ExtendedUserDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private extendedUserPopupService: ExtendedUserPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.extendedUserPopupService
                .open(ExtendedUserDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

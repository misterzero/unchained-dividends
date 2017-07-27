import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AlertService, EventManager } from 'ng-jhipster';

import { Master } from './master.model';
import { MasterPopupService } from './master-popup.service';
import { MasterService } from './master.service';

@Component({
    selector: 'jhi-master-delete-dialog',
    templateUrl: './master-delete-dialog.component.html'
})
export class MasterDeleteDialogComponent {

    master: Master;

    constructor(
        private masterService: MasterService,
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private eventManager: EventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.masterService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'masterListModification',
                content: 'Deleted an master'
            });
            this.activeModal.dismiss(true);
        });
        this.alertService.success('unchainedApp.master.deleted', { param : id }, null);
    }
}

@Component({
    selector: 'jhi-master-delete-popup',
    template: ''
})
export class MasterDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private masterPopupService: MasterPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.masterPopupService
                .open(MasterDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

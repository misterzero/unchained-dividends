import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AlertService, EventManager } from 'ng-jhipster';

import { Investor } from './investor.model';
import { InvestorPopupService } from './investor-popup.service';
import { InvestorService } from './investor.service';

@Component({
    selector: 'jhi-investor-delete-dialog',
    templateUrl: './investor-delete-dialog.component.html'
})
export class InvestorDeleteDialogComponent {

    investor: Investor;

    constructor(
        private investorService: InvestorService,
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private eventManager: EventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.investorService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'investorListModification',
                content: 'Deleted an investor'
            });
            this.activeModal.dismiss(true);
        });
        this.alertService.success('unchainedApp.investor.deleted', { param : id }, null);
    }
}

@Component({
    selector: 'jhi-investor-delete-popup',
    template: ''
})
export class InvestorDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private investorPopupService: InvestorPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.investorPopupService
                .open(InvestorDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

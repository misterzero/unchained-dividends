import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AlertService, EventManager } from 'ng-jhipster';

import { RoundOfInvestment } from './round-of-investment.model';
import { RoundOfInvestmentPopupService } from './round-of-investment-popup.service';
import { RoundOfInvestmentService } from './round-of-investment.service';

@Component({
    selector: 'jhi-round-of-investment-delete-dialog',
    templateUrl: './round-of-investment-delete-dialog.component.html'
})
export class RoundOfInvestmentDeleteDialogComponent {

    roundOfInvestment: RoundOfInvestment;

    constructor(
        private roundOfInvestmentService: RoundOfInvestmentService,
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private eventManager: EventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.roundOfInvestmentService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'roundOfInvestmentListModification',
                content: 'Deleted an roundOfInvestment'
            });
            this.activeModal.dismiss(true);
        });
        this.alertService.success('unchainedApp.roundOfInvestment.deleted', { param : id }, null);
    }
}

@Component({
    selector: 'jhi-round-of-investment-delete-popup',
    template: ''
})
export class RoundOfInvestmentDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private roundOfInvestmentPopupService: RoundOfInvestmentPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.roundOfInvestmentPopupService
                .open(RoundOfInvestmentDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

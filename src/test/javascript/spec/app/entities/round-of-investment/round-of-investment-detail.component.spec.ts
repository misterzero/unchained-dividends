import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { UnchainedTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { RoundOfInvestmentDetailComponent } from '../../../../../../main/webapp/app/entities/round-of-investment/round-of-investment-detail.component';
import { RoundOfInvestmentService } from '../../../../../../main/webapp/app/entities/round-of-investment/round-of-investment.service';
import { RoundOfInvestment } from '../../../../../../main/webapp/app/entities/round-of-investment/round-of-investment.model';

describe('Component Tests', () => {

    describe('RoundOfInvestment Management Detail Component', () => {
        let comp: RoundOfInvestmentDetailComponent;
        let fixture: ComponentFixture<RoundOfInvestmentDetailComponent>;
        let service: RoundOfInvestmentService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [UnchainedTestModule],
                declarations: [RoundOfInvestmentDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    RoundOfInvestmentService,
                    EventManager
                ]
            }).overrideTemplate(RoundOfInvestmentDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RoundOfInvestmentDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RoundOfInvestmentService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new RoundOfInvestment(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.roundOfInvestment).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});

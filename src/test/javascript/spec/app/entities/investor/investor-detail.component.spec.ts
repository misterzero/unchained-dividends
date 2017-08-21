import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { UnchainedTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { InvestorDetailComponent } from '../../../../../../main/webapp/app/entities/investor/investor-detail.component';
import { InvestorService } from '../../../../../../main/webapp/app/entities/investor/investor.service';
import { Investor } from '../../../../../../main/webapp/app/entities/investor/investor.model';

describe('Component Tests', () => {

    describe('Investor Management Detail Component', () => {
        let comp: InvestorDetailComponent;
        let fixture: ComponentFixture<InvestorDetailComponent>;
        let service: InvestorService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [UnchainedTestModule],
                declarations: [InvestorDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    InvestorService,
                    EventManager
                ]
            }).overrideTemplate(InvestorDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(InvestorDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InvestorService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Investor(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.investor).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});

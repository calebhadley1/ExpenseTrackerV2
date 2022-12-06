import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeTrendsChartComponent } from './home-trends-chart.component';

describe('HomeTrendsChartComponent', () => {
  let component: HomeTrendsChartComponent;
  let fixture: ComponentFixture<HomeTrendsChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeTrendsChartComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomeTrendsChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

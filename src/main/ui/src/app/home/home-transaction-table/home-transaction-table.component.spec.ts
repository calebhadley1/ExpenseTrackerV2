import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeTransactionTableComponent } from './home-transaction-table.component';

describe('HomeTransactionTableComponent', () => {
  let component: HomeTransactionTableComponent;
  let fixture: ComponentFixture<HomeTransactionTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeTransactionTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomeTransactionTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

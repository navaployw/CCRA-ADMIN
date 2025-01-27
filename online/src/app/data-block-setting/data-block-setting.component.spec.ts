import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DataBlockSettingComponent } from './data-block-setting.component';

describe('DataBlockSettingComponent', () => {
  let component: DataBlockSettingComponent;
  let fixture: ComponentFixture<DataBlockSettingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DataBlockSettingComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DataBlockSettingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

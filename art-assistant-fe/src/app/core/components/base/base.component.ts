import { OnDestroy, Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable()
export class BaseComponent implements OnDestroy {
  public unsubscribe$ = new Subject();

  constructor() {
  }

  ngOnDestroy() {
    // @ts-ignore
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
}

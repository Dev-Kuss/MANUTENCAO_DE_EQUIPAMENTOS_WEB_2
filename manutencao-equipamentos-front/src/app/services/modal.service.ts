import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})

export class DrawerService {
  private drawerState = new BehaviorSubject<boolean>(false);
  drawerState$ = this.drawerState.asObservable();

  openDrawer() {
    this.drawerState.next(true);
  }

  closeDrawer() {
    this.drawerState.next(false);
  }
}
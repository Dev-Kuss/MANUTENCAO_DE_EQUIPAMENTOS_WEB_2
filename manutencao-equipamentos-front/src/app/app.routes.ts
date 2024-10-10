import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { ClienteHomeComponent } from './pages/cliente-home/cliente-home.component';
import { FuncionarioHomeComponent } from './pages/funcionario-home/funcionario-home.component';
import { AutocadastroComponent } from './pages/autocadastro/autocadastro.component';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'cliente-home', component: ClienteHomeComponent },
  { path: 'funcionario-home', component: FuncionarioHomeComponent },
  { path: 'autocadastro', component: AutocadastroComponent },
];
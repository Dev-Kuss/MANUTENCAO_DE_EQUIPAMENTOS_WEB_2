import { Routes } from '@angular/router';
import { AutocadastroComponent } from './pages/autocadastro';
import { LoginComponent } from './pages/login';
import { ClienteHomeComponent } from './pages/cliente-home';
import { FuncionarioHomeComponent } from './pages/funcionario-home';
import { CategoriasCrudComponent } from './pages/categorias-crud';
import { FuncionarioCrudComponent } from './pages/funcionarios-crud';
import { authGuard } from './services/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'autocadastro', component: AutocadastroComponent },

  // Rotas protegidas pelo authGuard
  { path: 'cliente-home', component: ClienteHomeComponent, canActivate: [authGuard] },
  { path: 'funcionario-home', component: FuncionarioHomeComponent, canActivate: [authGuard] },
  { path: 'categorias-crud', component: CategoriasCrudComponent, canActivate: [authGuard] },
  { path: 'funcionario-crud', component: FuncionarioCrudComponent, canActivate: [authGuard] },
];

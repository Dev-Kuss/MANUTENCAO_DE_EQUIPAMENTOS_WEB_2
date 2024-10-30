import { Routes } from '@angular/router';
import { AutocadastroComponent } from './pages/autocadastro/autocadastro.component';
import { LoginComponent } from './pages/login/login.component';
import { ClienteHomeComponent } from './pages/cliente-home/cliente-home.component';
import { FuncionarioHomeComponent } from './pages/funcionario-home/funcionario-home.component';
import { CategoriasCrudComponent } from './pages/categorias-crud/categorias-crud.component';
import { FuncionarioCrudComponent } from './pages/funcionarios-crud/funcionarios-crud.component';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'cliente-home', component: ClienteHomeComponent },
  { path: 'funcionario-home', component: FuncionarioHomeComponent },
  { path: 'categorias-crud', component: CategoriasCrudComponent },
  { path: 'funcionario-crud', component: FuncionarioCrudComponent },
  { path: 'autocadastro', component: AutocadastroComponent },
];
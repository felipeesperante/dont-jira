const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/TreeBoardPage.vue') }
    ]
  }
];

export default routes;

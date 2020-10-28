import Vue from 'vue'
import Router from 'vue-router'

import signup from './components/view/signup'
import main from './components/view/main'
import listDetail from './components/view/listDetail'
import boardWrtie from './components/view/boardWrite'

Vue.use(Router)

export default new Router({
    mode:'history',
    routes: [
        {
            path:"/",
            name:"main",
            component:main
        },
        {
            path:"/signup",
            name:"signup",
            component:signup
        },
        {
            path:"/board/:boardName",
            name:"board",
            component:listDetail
        },
        {
            path:"/board/:boardName/write",
            name:"boardWrite",
            component:boardWrtie
        }
    ]
})
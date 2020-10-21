import Vue from 'vue'
import Router from 'vue-router'

import signup from './components/view/signup'
import main from './components/view/main'
Vue.use(Router)

export default new Router({
    mode:'history',
    routes: [
        {
            path:"/",
            name:"Main",
            component:main
        },
        {
            path:"/signup",
            name:"Signup",
            component:signup
        },
    ]
})
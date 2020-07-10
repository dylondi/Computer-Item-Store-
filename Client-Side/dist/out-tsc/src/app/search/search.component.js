import { __decorate } from "tslib";
import { Component } from '@angular/core';
let SearchComponent = class SearchComponent {
    constructor(service) {
        this.service = service;
    }
    findComponent() {
        let resp = this.service.searchComponent(this.name);
        resp.subscribe((data) => this.computerComponents = data);
    }
    ngOnInit() {
        let resp = this.service.getComponents();
        console.log(this.computerComponents + "hittttt");
        resp.subscribe((data) => this.computerComponents = data);
        console.log(this.computerComponents + "hittttt");
    }
};
SearchComponent = __decorate([
    Component({
        selector: 'app-search',
        templateUrl: './search.component.html',
        styleUrls: ['./search.component.css']
    })
], SearchComponent);
export { SearchComponent };
//# sourceMappingURL=search.component.js.map
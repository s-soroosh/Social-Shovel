'use strict';

/**
 * @ngdoc service
 * @name zssApp.DataService
 * @description
 * # DataService
 * Service in the zssApp.
 */
angular.module('zssApp')
  .service('DataService', function () {
      this.data=[0,0,0];
      this.countryData=[0,0,0,0,0];
        this.socialMediaMessages=[];
        this.mapRate=[
            ['Country', 'Popularity']
        ];
        this.trendsData = [{name:"Shoes",provider:"twitter",posts:332,languages:["DE","ENG"],period:"May-June"},
            {name:"Sneakers",provider:"twitter",posts:550,languages:["FR","ENG"],period:"May-June"},
            {name:"Jeans",provider:"twitter",posts:113,languages:["DE","ENG"],period:"May-June"},
            {name:"Boots",provider:"twitter",posts:120,languages:["DE","ENG"],period:"May-June"},
            {name:"Hats",provider:"twitter",posts:120,languages:["DE","ENG"],period:"May-June"},
            {name:"Bra",provider:"twitter",posts:120,languages:["DE","ENG"],period:"May-June"},
            {name:"Black boots",provider:"twitter",posts:31,languages:["IT"],period:"May-June"},
            {name:"Ponchoes",provider:"twitter",posts:45,languages:["DE","ENG"],period:"May-June"}
        ];
  });

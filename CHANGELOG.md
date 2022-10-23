## [0.4.0](https://github.com/Wynntils/Hades/compare/v0.3.0...v0.4.0) (2022-10-23)


### New Features

* Implement ping-pong packet pair ([#9](https://github.com/Wynntils/Hades/issues/9)) ([e17fa61](https://github.com/Wynntils/Hades/commit/e17fa61f0b2b68540a79b566aa57ae58ef440e7b))

## [0.3.0](https://github.com/Wynntils/Hades/compare/v0.2.3...v0.3.0) (2022-10-22)


### New Features

* HSPacketUpdateMutual and HCPacketUpdateStatus use float for location ([#8](https://github.com/Wynntils/Hades/issues/8)) ([e817e4e](https://github.com/Wynntils/Hades/commit/e817e4e90a6c60e2479832bcac047cb33723abf8))


### Miscellaneous Chores

* **release:** v0.3.0 [skip ci] ([c13fa8a](https://github.com/Wynntils/Hades/commit/c13fa8a68d06498fbea57abe02d1185b96b0ed8b))

## [0.2.3](https://github.com/Wynntils/Hades/compare/v0.2.2...v0.2.3) (2022-10-21)


### Bug Fixes

* HSPacketUpdateMutual and HCPacketUpdateStatus should not use doubles for health and mana info ([#7](https://github.com/Wynntils/Hades/issues/7)) ([808905b](https://github.com/Wynntils/Hades/commit/808905bfc5e6c9fcfb96694e05bb5562d4fb1aeb))


### Miscellaneous Chores

* **release:** v0.2.3 [skip ci] ([0bed88e](https://github.com/Wynntils/Hades/commit/0bed88e43531f24a11562e0628e43f374e68538c))

## [0.2.2](https://github.com/Wynntils/Hades/compare/v0.2.1...v0.2.2) (2022-10-21)


### Bug Fixes

* HSPacketUpdateMutual packet contains more information ([#6](https://github.com/Wynntils/Hades/issues/6)) ([68f3fe1](https://github.com/Wynntils/Hades/commit/68f3fe132e4c97adbea4e5b92ac280ce3027e5b1))


### Miscellaneous Chores

* **release:** v0.2.2 [skip ci] ([96f1ccf](https://github.com/Wynntils/Hades/commit/96f1ccf46b01108a15ff330435c9fadc9763ae21))

## [0.2.1](https://github.com/Wynntils/Hades/compare/v0.2.0...v0.2.1) (2022-10-21)


### Bug Fixes

* send packet to other players on status update ([67f3f9a](https://github.com/Wynntils/Hades/commit/67f3f9ac92716aae32c64c00947289f4d44d11f6))


### Miscellaneous Chores

* **release:** v0.2.1 [skip ci] ([d420527](https://github.com/Wynntils/Hades/commit/d42052776d6aaa71554dd571cde41baf4328d845))

## [0.2.0](https://github.com/Wynntils/Hades/compare/v0.1.0...v0.2.0) (2022-10-20)


### New Features

* Add new PacketAction type ([#3](https://github.com/Wynntils/Hades/issues/3)) ([295b966](https://github.com/Wynntils/Hades/commit/295b9662a02f18cf1fae43c437f1efe73f222276))


### Miscellaneous Chores

* **release:** v0.2.0 [skip ci] ([73411f8](https://github.com/Wynntils/Hades/commit/73411f88345cbcdb5e40ae685061b2c78a5212b6))
* remove node files [skip ci] ([5a906ab](https://github.com/Wynntils/Hades/commit/5a906ab0e4d7711f563855aed74b62b654d59a01))
* update readme [skip ci] ([ed5c10b](https://github.com/Wynntils/Hades/commit/ed5c10b779a723d0487e6d94c264d3e7108286b6))

## [0.1.0](https://github.com/Wynntils/Hades/compare/080de3183bc4aa5212b482cd9702c61faf83235a...v0.1.0) (2022-10-20)


### Bug Fixes

* fixed packet encode/encode ([04bf3e3](https://github.com/Wynntils/Hades/commit/04bf3e38d2841d8d61075d46e43bfa237b976a05))
* forgot to register server channels ([a19f258](https://github.com/Wynntils/Hades/commit/a19f25866f22a703cae1c23a3148387af4dcdfdb))
* inverted packet directions ([6d608b4](https://github.com/Wynntils/Hades/commit/6d608b4695f36f97c65ea15f25cde145946b29d2))
* properly attributed packet ids ([1669d70](https://github.com/Wynntils/Hades/commit/1669d70d42357fb6a5c4d18ab8de58578a24636a))
* removed debug ([04e77c1](https://github.com/Wynntils/Hades/commit/04e77c19f66880a670f0718a3c4ef3990e2d4af9))


### New Features

* added empty constructors to packets ([48f949e](https://github.com/Wynntils/Hades/commit/48f949e9ce603ba5c461bc59b3e88b8e5e1f4226))
* added flush packets ([d8661f7](https://github.com/Wynntils/Hades/commit/d8661f7f8882a51ecb1764f40700093152c17bb8))
* added packet HPacketClearMutual ([5442f83](https://github.com/Wynntils/Hades/commit/5442f83f1055d90f3eaacb6797df31860d761c74))
* added packet HPacketUpdateWorld ([6014fc1](https://github.com/Wynntils/Hades/commit/6014fc17200e29a6534b8933d90bad138a774359))
* added properly var int splitter and prepender ([977f12b](https://github.com/Wynntils/Hades/commit/977f12b1a8680f2d0a862491ebc3093c2989e82a))
* allowed exceptions to be printed ([eb672bc](https://github.com/Wynntils/Hades/commit/eb672bc5983ab05c8a995e06d6552dd13648776f))
* better enum handling ([0c9ce9f](https://github.com/Wynntils/Hades/commit/0c9ce9f4326d94e1e7283ebaf4543be171a89b7d))
* complete rewrite ([080de31](https://github.com/Wynntils/Hades/commit/080de3183bc4aa5212b482cd9702c61faf83235a))
* Overall Structural Changes ([a8b4808](https://github.com/Wynntils/Hades/commit/a8b48081c73f57b29b499e3b10382f3d635b223f))


### Miscellaneous Chores

* add github release ([298f812](https://github.com/Wynntils/Hades/commit/298f812374c4290e2cf6c33944dfc282ee1f41cd))
* add github workflow ([58fb559](https://github.com/Wynntils/Hades/commit/58fb559dc097e2aaf88c3240c50b1078a5a4d96e))
* add max values ([af96219](https://github.com/Wynntils/Hades/commit/af962197cfc53f0cf254e4a3bf89ef891067f020))
* bugfix + add onConnect event ([972825f](https://github.com/Wynntils/Hades/commit/972825f9d79eab6efcd337a0befe1cc26ea91b26))
* **release:** v0.1.0 [skip ci] ([96e97dd](https://github.com/Wynntils/Hades/commit/96e97dd63d72274c421368b321bc2d2945a32b5d))
* remove default from interface ([71def20](https://github.com/Wynntils/Hades/commit/71def2061955f26455c340483cc203f8b2f87dd4))
* update file permissions ([7ab3f18](https://github.com/Wynntils/Hades/commit/7ab3f1896778e0e4453e20858463fc76c7999d01))
* update gradle ([6684db3](https://github.com/Wynntils/Hades/commit/6684db3d32a7dbd4fbcfe425cc1509d28431d7cd))
* update guild packet ([c81e64d](https://github.com/Wynntils/Hades/commit/c81e64d05b8c3b81ad90eea15bc8c002af5a7664))
* update java version ([d614191](https://github.com/Wynntils/Hades/commit/d614191d1573dbc5aa00d7db5c305370ce89928e))
* updates ([332f280](https://github.com/Wynntils/Hades/commit/332f280ae02d08fc7412e6476cee479e89e43cbe))


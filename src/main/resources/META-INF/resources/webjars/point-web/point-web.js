;(function(window, document, $){
    var Event = (function () {
        var global = this,
            Event,
            _default = 'default';

        Event = function () {
            var _listen,
                _trigger,
                _remove,
                _shift = Array.prototype.shift,
                _unshift = Array.prototype.unshift,
                namespaceCache = {},
                _create,
                each = function (ary, fn) {
                    var ret;
                    for (var i = 0, l = ary.length; i < l; i++) {
                        var n = ary[i];
                        ret = fn.call(n, i, n);
                    }
                    return ret;
                };

            _listen = function (key, fn, cache) {
                if (!cache[key]) {
                    cache[key] = [];
                }
                cache[key].push(fn);
            };

            _remove = function (key, cache, fn) {
                if (cache[key]) {
                    if (fn) {
                        for (var i = cache[key].length; i >= 0; i--) {
                            if (cache[key][i] === fn) {
                                cache[key].splice(i, 1);
                            }
                        }
                    } else {
                        cache[key] = [];
                    }
                }
            };

            _trigger = function () {
                var cache = _shift.call(arguments),
                    key = _shift.call(arguments),
                    args = arguments,
                    _self = this,
                    ret,
                    stack = cache[key];

                if (!stack || !stack.length) {
                    return;
                }

                return each(stack, function () {
                    return this.apply(_self, args);
                });
            };

            _create = function (namespace) {
                var namespace = namespace || _default;
                var cache = {},
                    offlineStack = [],    // 离线事件
                    ret = {
                        listen: function (key, fn, last) {
                            _listen(key, fn, cache);
                            if (offlineStack === null) {
                                return;
                            }
                            if (last === 'last') {
                                offlineStack.length && offlineStack.pop()();
                            } else {
                                each(offlineStack, function () {
                                    this();
                                });
                            }

                            offlineStack = null;
                        },
                        one: function (key, fn, last) {
                            _remove(key, cache);
                            this.listen(key, fn, last);
                        },
                        remove: function (key, fn) {
                            _remove(key, cache, fn);
                        },
                        trigger: function () {
                            var fn,
                                args,
                                _self = this;

                            _unshift.call(arguments, cache);
                            args = arguments;
                            fn = function () {
                                return _trigger.apply(_self, args);
                            };

                            if (offlineStack) {
                                return offlineStack.push(fn);
                            }
                            return fn();
                        }
                    };

                return namespace ?
                    (namespaceCache[namespace] ? namespaceCache[namespace] :
                        namespaceCache[namespace] = ret)
                    : ret;
            };

            return {
                create: _create,
                one: function (key, fn, last) {
                    var event = this.create();
                    event.one(key, fn, last);
                },
                remove: function (key, fn) {
                    var event = this.create();
                    event.remove(key, fn);
                },
                listen: function (key, fn, last) {
                    var event = this.create();
                    event.listen(key, fn, last);
                },
                trigger: function () {
                    var event = this.create();
                    event.trigger.apply(this, arguments);
                }
            };
        }();
        return Event;
    })();
    window.Event = Event;

    function __format(n){
        n = n.replace(/[.\s\\/]/g, '_');
        return n;
    }

    window.app = {
        $:function(n){
            return document.querySelector(n);
        },
        load:function(context){var self = this; return $.get(context?context:''+'/fragment/bundle', function(data){self.bundle=data.data;});},
        script:function(t, a, c){
            var s = document.createElement("script");
            for(var r in a){
                s.setAttribute(r, a[r]?a[r]:null);
            }
            if(t){s.innerHTML=t;}
            if(c){s.onload=c;}
            document.body.appendChild(s);
        },
        run:function(bundleJs){
            let js = this.bundle[bundleJs];
            if(js){
                let id = __format(bundleJs);
                let d = this.$('#'+id);
                if(d){document.body.removeChild(d);}
                this.script(js, {id: id});
            }
        }
    };
}(window, document, jQuery));
/**
 * Created by WYX on 2018/11/26.
 */
BUI.use(['common/search', 'bui/tab', 'bui/tooltip', 'bui/grid', 'bui/select', 'bui/picker', 'bui/extensions/treepicker', 'bui/tree'],
    function (Search, Tab, Tooltip, Grid, Select, Picker, TreePicker, Tree) {
        var offices_select = localstorageIO("offices_select", "/getOfficeSelect");
        var enumObj = {"0": "未出库", "1": "已出库"},
            select_data = [],
            select_type = [],
            stock_office = {},
            search_type_select = new Select.Select({
                render: '#search_type_select',
                valueField: '#search_type',
                multipleSelect: false,
                items: [
                    {text: '设备', value: '1'},
                    {text: '配件', value: '2'},
                    {text: '耗材', value: '3'}
                ],
            }).render(),
            Editing_I = new BUI.Grid.Plugins.DialogEditing({
                contentId: 'Content_I', //设置隐藏的Dialog内容
                autoSave: true, //添加数据或者修改数据时，自动保存
                triggerCls: 'btn-edit'
            }),
            Editing_O = new BUI.Grid.Plugins.DialogEditing({
                contentId: 'Content_O', //设置隐藏的Dialog内容
                autoSave: true, //添加数据或者修改数据时，自动保存
                triggerCls: 'btn-out_dev'
            }),
            tab_I = new Tab.TabPanel({
                render: '#tab_I',
                elCls: 'button-tabs',
                panelContainer: '#panel',//如果内部有容器，那么会跟标签项一一对应，如果没有会自动生成
                //selectedEvent : 'mouseenter',//默认为click,可以更改事件
                children: [
                    {id: 'dev_row', title: '设备', name: 'DeviceSelect', value: '1', selected: true},
                    {id: 'matP_row', title: '配件', name: 'MaterialSelect', value: '2',},
                    {id: 'matH_row', title: '耗材', name: 'MaterialSelect', value: '3',},
                ]
            }).render(),
            dev_name_select = new Select.Select({
                render: '#dev_name_select',
                valueField: '#dev_name',
                multipleSelect: false,
            }).render(),
            dev_type_select = new Select.Select({
                render: '#dev_type_select',
                valueField: '#entity_id',
                multipleSelect: false,
                items: []
            }).render(),
            stock_office_select = new Select.Select({
                render: '#stock_office_select',
                valueField: '#stock_office_id',
                multipleSelect: false,
                items: []
            }).render(),
            Sto_rec_grid = new Grid.SimpleGrid({
                idField: 'stock_ident',//选项的默认id字段
                columns: [
                    {title: '库存编号', dataIndex: 'stock_ident', elCls: 'center', width: '30%'},
                    {title: '数量', dataIndex: 'stock_no', elCls: 'center', width: '20%'},
                    {title: '单位', dataIndex: 'stock_unit', elCls: 'center', width: '20%'},
                    {title: '总量', dataIndex: 'stock_total', elCls: 'center', width: '30%'},
                ],
                textGetter: function (item) { //返回选中的文本
                    return item.b;
                },
                items: [],
            }),
            stock_record_picker = new Picker.ListPicker({
                width: 300,  //指定宽度
                children: [Sto_rec_grid] //配置picker内的列表
            }),
            stock_record_select = new Select.Select({
                picker: stock_record_picker,
                render: '#stock_record_select',
                valueField: '#stock_record',
                forceFit: false,
                multipleSelect: false,
                width: 300,
                items: []
            }).render(),
            stock_office_tree = new Tree.TreeList({
                nodes: [],
                //store: store,
                //dirSelectable : false,//阻止树节点选中
                showLine: true //显示连接线
            }),
            stock_office_picker = new TreePicker({
                trigger: '#',
                //valueField: '#hide', //如果需要列表返回的value，放在隐藏域，那么指定隐藏域
                width: 150,  //指定宽度
                children: [stock_office_tree] //配置picker内的列表
            }).render(),
            columns = [
                {title: '&nbsp库存编号', dataIndex: 'stock_ident', elCls: 'center', width: 110},
                {title: '&nbsp库存名称', dataIndex: 'entity_name', elCls: 'center', width: 110},
                {title: '&nbsp库存型号', dataIndex: 'entity_type', elCls: 'center', width: 130},
                {title: '库存类别', dataIndex: 'stock_type', elCls: 'center', width: 70},
                {title: '&nbsp数量', dataIndex: 'stock_no', elCls: 'center', width: 70},
                {title: '&nbsp库存单位', dataIndex: 'stock_unit', elCls: 'center', width: 100},
                {title: '&nbsp总量', dataIndex: 'stock_total', elCls: 'center', width: 100},
                {title: '&nbsp库存状态', dataIndex: 'stock_flag', elCls: 'center', width: 100},
                {id: 'remark', title: '&nbsp备注', dataIndex: 'remark', elCls: 'center', width: 150},
                {
                    title: '操作', dataIndex: '', width: 100, renderer: function (value, obj) {

                    var editStr1 = '<span class="grid-command btn-edit" title="编辑信息">编辑</span>',
                        delStr = '<span class="grid-command btn-del" title="删除信息">删除</span>';//页面操作不需要使用Search.createLink
                    return editStr1 + delStr;
                }
                }
            ],
            store = Search.createStore('/listStock', {
                proxy: {
                    save: { //也可以是一个字符串，那么增删改，都会往那么路径提交数据，同时附加参数saveType
                        addUrl: '/addStock',
                        updateUrl: '/updateStorage',
                        removeUrl: '../data/del.php'
                    },
                    method: 'POST'
                },
                pageSize: 6,
                autoSync: true //保存数据后，自动更新
            }),
            gridCfg = Search.createGridCfg(columns, {
                tbar: {
                    items: [
                        {
                            text: '<i class="icon-plus"></i>设备入库',
                            btnCls: 'button button-small',
                            handler: in_storageFunction
                        },
                        {
                            text: '<i class="icon-hand-right"></i>设备出库',
                            btnCls: 'button button-small',
                            handler: outDevFunction
                        },
                        {
                            text: '<i class="icon-list"></i><a>入库记录</a>',
                            btnCls: 'button button-small',
                            handler: storageFunction
                        },
                        {
                            text: '<i class="icon-list"></i><a>出库记录</a>',
                            btnCls: 'button button-small',
                            handler: storageFunction
                        }
                    ]
                },
                plugins: [Editing_I, Editing_O, BUI.Grid.Plugins.CheckSelection, BUI.Grid.Plugins.AutoFit],// 插件形式引入多选表格
                emptyDataTpl: '<div class="centered"><img alt="Crying" src="http://img03.taobaocdn.com/tps/i3/T1amCdXhXqXXXXXXXX-60-67.png"><h2>未获得到数据</h2></div>',
            });

        var search = new Search({
                store: store,
                gridCfg: gridCfg
            }),
            grid = search.get('grid'),
            I_Form = Editing_I.get('form'),
            O_Form = Editing_O.get('form');
        var search_grid_tip = new Tooltip.Tip({
            trigger: '.grid-td-remark', //出现此样式的元素显示tip
            alignType : 'bottom-left',
            offset : 1,
            width:100,
            title : '',
            elCls : 'tips tips-no-icon',
            titleTpl : '<p>{title}</p>'
        }).render();

        function storageFunction() {
            if (top.topManager) {  //打开左侧菜单中配置过的页面
                top.topManager.openPage({
                    id: 'storage-menu',
                    href: 'menu/storage_tab',
                    title: '入库记录'
                });
            }
        }

        //入库输入框重置
        function I_Reset() {
            I_Form.clearFields();
            stock_office_select.setSelectedValue();
            dev_type_select.setSelectedValue();
            dev_name_select.setSelectedValue();
            input_console(true);
        }

        //禁止输入框输入
        function input_console(dis) {
            $("#I_Input_console input").each(function () {
                $(this).attr('disabled', dis);
            })
        }

        //下拉框ajax请求entity_selectAjax
        function entity_selectAjax() {
            var url = '/get' + tab_I.getSelected().__attrVals.name;
            if ($.isEmptyObject(select_data)) {
                $.ajax({
                    url: url,
                    data: {tab: tab_I.getSelected().__attrVals.value},
                    type: 'POST', //GET
                    async: true,    //或false,是否异步
                    /*timeout: 5000,*/    //超时时间(如果后台数据加载缓慢，设置超时时间会导致在时间超时后自动执行success函数而后台还没有返回data数据)
                    dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                    success: function (data, textStatus, jqXHR) {
                        if (data != null && data != undefined) {
                            select_data = data;
                            dev_name_select.set('items', select_data.Class_data);
                        }
                    },
                    error: function (xhr, textStatus) {
                        console.log('错误')
                        console.log(xhr)
                        console.log(textStatus)
                    }
                })
            }
        }

        //下拉框ajax请求office_selectAjax
        function office_selectAjax() {
            if (offices_select == undefined || offices_select == null) {
                offices_select = localstorageIO("offices_select", "/getOfficeSelect");
                if (offices_select == null) {   //offices_select为null表示浏览器不支持localstorage
                    alert("浏览器不支持localstorage！");
                    $.ajax({
                        url: '/getOfficeSelect',
                        type: 'POST', //GET
                        async: true,    //或false,是否异步
                        /*timeout: 5000,*/    //超时时间(如果后台数据加载缓慢，设置超时时间会导致在时间超时后自动执行success函数而后台还没有返回data数据)
                        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                        success: function (data, textStatus, jqXHR) {
                            if (data != null && data != undefined) {
                                offices_select = data.result;
                                stock_office_select.set('items', stock_officeSelect());
                                /*var mObj = GetMenus('root', offices_select);
                                 stock_office_tree.set('nodes', mObj);*/
                            }
                        },
                        error: function (xhr, textStatus) {
                            console.log('错误')
                            console.log(xhr)
                            console.log(textStatus)
                        }
                    })
                }
            }
        }

        //库存科室下拉框数据生成
        function stock_officeSelect() {
            if (stock_office == undefined || $.isEmptyObject(stock_office)) {
                for (var i in offices_select) {
                    if (offices_select[i].office_function == '1') {
                        stock_office[offices_select[i].value] = offices_select[i].text;
                    }
                }
            }
            return stock_office;
        }

        //入库总量生成
        function reckon_stock_total(num, stock_proportion) {  //（输入的库存数量 库存比例)
            //I_Form.getField('in_confirmed_no').get('value');
            var unit = parseInt(num);//获取整数部分
            var total = unit * stock_proportion;
            total = total + num % unit;
            return total;
        }

        //根据比例调整库存数量
        function calculate_amount(num, stock_proportion) { //数量，比例数
            //I_Form.getField('in_confirmed_no').get('value');
            stock_proportion = parseInt(stock_proportion);//防止比例数输入为小数
            var unit = num;
            var decimal = 1;
            var mu = parseInt(num);
            while (decimal > 0) {
                unit = unit * 10;
                decimal = unit % 10;
                mu = mu * 10;
            }
            decimal = unit / 10 - mu / 10;//获取小数部分
            if (decimal > stock_proportion) {
                unit = parseInt(num);
                mu = parseInt(decimal / stock_proportion);//求小数部分有多少单位数量
                unit = unit + mu; //单位数量相加
                decimal = decimal - (mu * stock_proportion);
                while (decimal >= 1) {
                    decimal = decimal / 10;
                }
                num = unit + decimal;
            }
            return num;
        }

        //入库操作
        function in_storageFunction() {

            entity_selectAjax();
            office_selectAjax();
            dev_type_select.set('items', []);
            dev_name_select.set('items', select_data.Class_data);
            stock_office_select.set('items', stock_officeSelect());
            var newData = {isNew: true}; //标志是新增加的记录
            Editing_I.add(newData, 'name'); //添加记录后，直接编辑
            I_Form.getField('io_Console').set('value', '1');
            I_Form.getField('stock_type').set('value', tab_I.getSelected().__attrVals.value);
            input_console(true);
        }

        //编辑操作
        function updateFunction() {
            var selections = grid.getSelection();
            if (selections.length == 0) {
                return;
            } else if (selections.length != 1) {
                BUI.Message.Alert('一次只能编辑一条记录！！');
                return;
            } else {
                var eData = selections[0];
                Editing_I.edit(eData);
            }
        }

        //出库操作
        function outDevFunction() {
            var selections = grid.getSelection();
            if (selections.length == 0) {
                return;
            } else if (selections.length != 1) {
                BUI.Message.Alert('一次只能选择单个设备出库！！');
                return;
            } else {
                var eData = selections[0];
                Editing_O.edit(eData);
                O_Form.getField('used_no').addRule('max', selections[0].dev_no, '库存不足，不能大于{0}');
            }
        }

        //删除操作
        function delFunction() {
            var selections = grid.getSelection();
            delItems(selections);
        }

        function delItems(items) {
            var ids = [];
            BUI.each(items, function (item) {
                ids.push(item.id);
            });
            if (ids.length) {
                BUI.Message.Confirm('确认要删除选中的记录么？', function () {
                    store.save('remove', {ids: ids});
                }, 'question');
            }
        }

        //监听事件，删除一条记录
        grid.on('cellclick', function (ev) {
            var sender = $(ev.domTarget); //点击的Dom
            if (sender.hasClass('btn-del')) {
                var record = ev.record;
                delItems([record]);
            }
        });

        //监听事件，tab类别切换
        tab_I.on('selectedchange', function (ev) {
            I_Reset();
            select_data = [];
            entity_selectAjax();
        });

        //监听事件，选择设备种类下拉框选中
        dev_name_select.on('change', function (ev) {
            select_type = [];
            var Entity_data = select_data.Entity_data;
            for (var j = 0, len = Entity_data.length; j < len; j++) {
                if (Entity_data[j].dev_class_id == dev_name_select.getSelectedValue()) {
                    select_type.push(Entity_data[j]);
                }
            }
            dev_type_select.set('items', select_type);
        });

        //监听事件，选择设备型号下拉框选中
        dev_type_select.on('change', function (ev) {

        });

        //提示框显示
        search_grid_tip.on('show', function (ev) {
            var innerText = ev.target.__attrVals.curTrigger.context.innerText;
            search_grid_tip.set('title',innerText);
        });

        //监听事件，选择科室下拉框选中
        stock_office_select.on('change', function (ev) {
            input_console(true);
            var edv = Editing_I.get('editor').getValue();
            I_Form.getField('unit_num').set('value', '1');
            if (edv.entity_id != null && edv.entity_id != '' && edv.entity_id != undefined) {
                $.ajax({
                    url: '/listStockByEntityId',
                    data: {
                        entity_id: edv.entity_id,
                        stock_office: edv.stock_office_id
                    },
                    type: 'POST', //GET
                    async: true,    //或false,是否异步
                    /*timeout: 5000,*/    //超时时间(如果后台数据加载缓慢，设置超时时间会导致在时间超时后自动执行success函数而后台还没有返回data数据)
                    dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                    success: function (data, textStatus, jqXHR) {
                        if (!$.isEmptyObject(data.entityData)) {
                            Sto_rec_grid.set('items', data.entityData);
                            // input_console(false);
                        } else {
                            Sto_rec_grid.set('items',[]);
                            input_console(false);
                        }
                    },
                    error: function (xhr, textStatus) {
                        console.log('错误')
                        console.log(xhr)
                        console.log(textStatus)
                    }
                })
            }//if语句判断是否获取到设备ID
        });

        $("#I_Reset").click(function () {
            I_Reset();
            /*   I_Form.getField('reset').on('click', function (ev) {  //重置按钮

             });
             I_Form.getField('stock_proportion').on('change', function (ev) { //根据比例调整数量
             alert(I_Form.getField('stock_proportion').get('value'));
             });*/
        });
        $("#stock_proportion").on('blur', function () {
            var I_num = $(" #in_confirmed_no ").val();
            var stock_proportion = I_Form.getField('stock_proportion').get('value');
            if (I_num != null && I_num != '' && I_num != undefined) {
                I_num = calculate_amount(I_num, stock_proportion);
                I_Form.getField('in_confirmed_no').set('value', I_num);
            }
        });

    });
// JTable script

$(document).ready(function () {
    $('#LocationTableContainer').jtable({
        title: 'Available locations',
        actions: {
            listAction: 'LocationList.do',
            createAction: 'LocationSave.do',
            updateAction: 'LocationUpdate.do',
            deleteAction: 'LocationDelete.do'
        },
        fields: {
            id: {
                key: true,
                title: 'ID',
                width: '6%',
                create: false,
                edit: false
            },
            city: {
                title: 'City',
                width: '20%',
                inputClass: 'validate[required]'
            }
        },
        formCreated: function (event, data) {
            //Initialize validation logic when a form is created
            data.form.validationEngine('attach', {
                promptPosition: "bottomLeft"
            });
        },
        formSubmitting: function (event, data) {
            //Validate form when it is being submitted
            return data.form.validationEngine('validate');
        },
        formClosed: function (event, data) {
            //Dispose validation logic when form is closed
            data.form.validationEngine('hide');
            data.form.validationEngine('detach');
        }
    }).jtable('load');
});
<%@ page import="com.adobe.granite.ui.components.Config"%>
<%@ page import="org.slf4j.Logger"%>
<%@ page import="org.slf4j.LoggerFactory"%>
<%@ page import="com.adobe.granite.ui.components.Value"%>
<%@ page import="org.apache.commons.lang3.StringUtils"%>
<%@include file="/libs/granite/ui/global.jsp"%>

<%--include ootb multifield--%>
<sling:include
  resourceType="/libs/granite/ui/components/foundation/form/multifield" />

<%!
    private final Logger mLog = LoggerFactory.getLogger(this.getClass());
%>

<%
    Config mCfg = cmp.getConfig();

    Resource mField = mCfg.getChild("field");
 
    if (mField == null) {
        mLog.warn("Field node doesn't exist");
        return;
    }
 
    ValueMap mVM = mField.adaptTo(ValueMap.class);
 
    String mName = mVM.get("name", "");

    if ("".equals(mName)) {
        mLog.warn("name property doesn't exist on field node");
        return;
    }
 
    Value mValue = ((ComponentHelper) cmp).getValue();

    //get the values added in multifield
    String[] mItems = mValue.get(mName, String[].class);
%>

<script>
    (function () {
        var DATA_EAEM_LENGHT= "data-eaem-lenght";
		
        function isCheckbox($field) {
            //return !_.isEmpty($field) && ($field.prop("type") === "checkbox");
        	return ($field.prop("type") === "checkbox");
        }

        function setCheckBox($field, value) {
            $field.prop("checked", $field.attr("value") === value);
        }

        //function to add values into multifield widgets. The values are stored in CRX by collectDataFromFields() as json
        //eg. {"page":"English","path":"/content/geometrixx/en"}
        var addDataInFields = function () {
            var mValues = [ <%= StringUtils.join(mValue.get(mName, String[].class), ",") %> ],
                    mName = '<%=mName%>',http://localhost:4502/siteadmin#/content/geometrixx/en
                    $fieldSets = $("[class='coral-Form-fieldset'][data-name='" + mName + "']");

            var record, $fields, $field, name;
 
            $fieldSets.each(function (i, fieldSet) {
                $fields = $(fieldSet).find("[name]");
                

                record = mValues[i];
 
                if (!record) {
                    return;
                }

                $fields.each(function (j, field) {
                    $field = $(field);

                    name = $field.attr("name");

                    if (!name) {
                        return;
                    }

                    //strip ./
                    if (name.indexOf("./") == 0) {
                        name = name.substring(2);
                    }

                    if(isCheckbox($field)) {
                      setCheckBox($field, record[name]);
                    } else if($field.attr("class") === "coral-ColorPicker-button coral-MinimalButton") {
                        $field.css('background-color', record[name]);
                    } else if($field.attr("class") === "js-coral-DatePicker-input coral-InputGroup-input coral-Textfield") {
                    	if(record[name] != undefined && record[name] != ''){
                    		var date = new Date (record[name]);
                    		var yyyy = date.getFullYear();
                   		    var mm = date.getMonth() < 9 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1);
                   		    var dd  = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                   		    var newdate = yyyy + "-" + mm + "-" + dd;
                   		    $field.val(newdate);
                    	}else{
                    		$field.val('');
                    	}
                        
                    }else {
                    	$field.val(record[name]);
                    }

                });
            });
            $('['+DATA_EAEM_LENGHT+']')[DATA_EAEM_LENGHT]();
        };


        //collect data from widgets in multifield and POST them to CRX as JSON
        var collectDataFromFields = function(){
            $(document).on("click", ".cq-dialog-submit", function () {
                var $form = $(this).closest("form.foundation-form"), mName = '<%=mName%>';
                $('input[name="' + mName + '"]').remove();
 
                //get all the input fields of multifield
                var $fieldSets = $("[class='coral-Form-fieldset'][data-name='" + mName + "']");
 
                var record, $fields, $field, name;

                $fieldSets.each(function (i, fieldSet) {
                    $fields = $(fieldSet).find("[name]");
 
                    record = {};
 
                    $fields.each(function (j, field) {
                        $field = $(field);

                        name = $field.attr("name");
 
                        if (!name) {
                            return;
                        }

                        //strip ./
                        if (name.indexOf("./") == 0) {
                            name = name.substring(2);
                        }

                        if($field.attr('aria-required') === 'true' && $field.val().length <= 0) {
                          return;
                        }

                        if (isCheckbox($field)) {
                            record[name] = $field.prop("checked") ? $field.val() : null;
                        } else if($field.attr("class") === "coral-ColorPicker-button coral-MinimalButton"){
                    	  	var input = $($field.parent()).find('input[name="' + name + '"]');
                    	  	$(input).val($field.css("background-color"));
                        }else{
                        	record[name] = $field.val();
                        }
 
                        //remove the field, so that individual values are not POSTed
                        //  $field.remove();
                    });
 
                    if ($.isEmptyObject(record)) {
                      return;
                    }

                    //add the record JSON in a hidden field as string
                    $('<input />').attr('type', 'hidden')
                            .attr('name', mName)
                            .attr('value', JSON.stringify(record))
                            .appendTo($form);
                });
            });
        };
        
        function Plugin(element, options) {
            this.element = $(element);
            this.options = $.extend({}, this.element.data(), options);
            this.init();
          }
          var checkButton = function(el, $btnAdd, number) {
            var liTag = el.find('.coral-Multifield-input');
            $btnAdd = el.find('.js-coral-Multifield-add');
            if (liTag.length >= number) {
              $btnAdd.prop('disabled', true);
            } else {
              $btnAdd.prop('disabled', false);
            }
          };
      
          Plugin.prototype = {
            init : function() {
              var that = this, el = that.element;
              options = that.options.eaemLenght;
      
              var $liTag = el.find('.coral-Multifield-input');
              var $btnAdd = el.find('.js-coral-Multifield-add');
              // Checking init
              if ($liTag.length > options) {
                $btnAdd.prop('disabled', true);
              }
              el.bind("DOMSubtreeModified", function() {
                checkButton(el, $btnAdd, options);
              });
            },
            destroy : function() {
              // remove events
              // deinitialize
              $.removeData(this.element[0], pluginName);
            }
          };
      
          $.fn[DATA_EAEM_LENGHT] = function(options, params) {
      
            return this.each(function() {
              var instance = $.data(this, DATA_EAEM_LENGHT);
              if (!instance) {
                $.data(this, DATA_EAEM_LENGHT, new Plugin(this, options));
              } else if (instance[options]) {
                instance[options](params);
              }
            });
          };
                
          $(document).ready(function() {
            addDataInFields();
            collectDataFromFields();
          });
  })();
</script>

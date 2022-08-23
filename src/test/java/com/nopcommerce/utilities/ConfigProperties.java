package com.nopcommerce.utilities;

import org.aeonbits.owner.Config;

//Implementation of owner API can also use boolean, list in methods and default values
//Refer official documentation for more detail here http://owner.aeonbits.org/docs/usage/
@Config.Sources({"file:${user.dir}/resources/config.properties"})
public interface ConfigProperties extends Config {

    String baseURL();
    String useremail();
    String password();
    String runmode();
}
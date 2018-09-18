define([], function () {
    var configLocal = {};

    // clearing local storage otherwise source cache will obscure the override settings
    localStorage.clear();

    // WebAPI (TODO: Change This)
    configLocal.api = {
        name: 'OHDSI',
        url: '/WebAPI/'
    };

    configLocal.cohortComparisonResultsEnabled = false;
    configLocal.userAuthenticationEnabled = false;
    configLocal.plpResultsEnabled = false;

    return configLocal;
});

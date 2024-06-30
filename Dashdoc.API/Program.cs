using Dashdoc.API.Domain.Settings;
using Dashdoc.API.Server.StartupConfigurations;
using ConfigurationManager = System.Configuration.ConfigurationManager;

var builder = WebApplication.CreateBuilder(args);

builder.Configuration.AddSecretsManagerConfig();

# region Register App Services
builder.Services.AddMemoryCache();
builder.Services.AddControllers();

// Swagger Support
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

// AppConfig Setup
builder.Services.Configure<RdsAppSettings>(builder.Configuration);

// DB Config
builder.Services.RegisterDashdocDatabase();

// Cognito config

// Stripe Config
// builder.Services.RegisterStripeClient();
#endregion

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.Run();
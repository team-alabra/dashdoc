using System.Text.Json.Serialization;
using Dashdoc.API.Server.StartupConfigurations;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.Extensions.Configuration;

var builder = WebApplication.CreateBuilder(args);

# region Register App Services
builder.Services.AddMemoryCache();
builder.Services.AddCors();
builder.Services.AddControllers().AddJsonOptions(x =>
{
    // serialize enums as strings in api responses (e.g. Role)
    x.JsonSerializerOptions.Converters.Add(new JsonStringEnumConverter());

    // ignore omitted parameters on models to enable optional params (e.g. User update)
    x.JsonSerializerOptions.DefaultIgnoreCondition = JsonIgnoreCondition.WhenWritingNull;
});

// Automapper
builder.Services.AddAutoMapper(typeof(Program));
builder.Configuration.AddUserSecrets<Program>();

// Swagger Support
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

// DB Config
builder.Services.RegisterDashdocDatabase(builder.Configuration);

// Local Services Configuration
builder.Services.RegisterAppServices();

// Auth
if (builder.Environment.IsEnvironment("Development"))
{
    builder.Services.AddAuthorization();
    builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
        .AddJwtBearer();
    builder.Services.ConfigureOptions<JwtBearerConfiguration>();
}

#endregion

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsEnvironment("Development"))
{
    app.UseSwagger();
    app.UseSwaggerUI();
    app.UseAuthorization();
    app.UseAuthentication();
}

app.MapControllers();
app.UseHttpsRedirection();

// Defers app routing to the React App
app.MapFallbackToFile("/index.html");

app.Run();
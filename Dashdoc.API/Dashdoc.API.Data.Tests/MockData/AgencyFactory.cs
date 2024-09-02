using Bogus;
using Dashdoc.API.Domain.Entities;

namespace Dashdoc.API.Data.Tests.MockData;

public static class AgencyFactory
{
    private static readonly Random _random;

    static AgencyFactory()
    {
        _random = new Random();
    }

    public static Faker<Agency> GetFaker()
    {
        var mockEntity = new Faker<Agency>()
            .RuleFor(e => e.Id, _random.Next())
            .RuleFor(e => e.Name, x => x.Name.FirstName())
            .RuleFor(e => e.Email, x => x.Internet.Email()); // using the bogus library to get a random name (for now we can only get people's names)

        return mockEntity;
    }
    
}